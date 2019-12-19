const mongoose = require("mongoose");
const UserSC = require("./modules/user");
const MessageSC = require("./modules/messages");



const uri = "mongodb+srv://" + process.env.DB_USER + ":" + process.env.DB_PASS
    + "@android-chat-db-shop-ekenn.gcp.mongodb.net/message?retryWrites=true&w=majority";

mongoose.connect(uri,
    {
        useNewUrlParser: true,
        useUnifiedTopology: true,
        useFindAndModify: true
    },
    (error) => {
        if (error) handleError(error);
        else {
            console.log("connect successful")
        }
    }
);

let db = mongoose.connection;
let Model = mongoose.model('messages', UserSC);

db.on('error', console.error.bind(console, 'MongoDB connection error:'));

db.findUsers = () => {
    Model.find().lean().select('id_user').exec((err, res) => {
        if (err) { 
            console.log(err);
            return null;
        }
        else {
            console.log(res);
            return JSON.stringify(res);
        }
    });
}

db.findMess = (id) => {
    Model.find({ 'id_user': id }).exec((err, res) => {
        if (err) { 
            console.log(err); 
            return null;
        }
        else {
            console.log(res[0].get("mess", Array));
            return JSON.stringify(res[0].get("mess", Array));
        }
    });
}

db.addMess = (_id, _mess, _send) => {
    if(_id === 1){
        console.log("ID wrong");
        return;
    }
    Model.exists({'id_user': _id}, async (err, res) => {
        if(!res) {
            // Tao ra SC moi de add tin nhan vao
            const user = new Model({id_user: _id, mess: [{detail: _mess, send: _send}]});
            //console.log(user);
            await user.save().then((_res)=>{
                if(_res) {
                    return true;
                    console.log("save:" + _res);
                }
            });
        } else {
            // Cap nhat tin nhan vao document (update)
            Model.findOneAndUpdate({id_user: _id}, {$push: {mess: {detail: _mess, send: _send}}}, (err, res) =>{
                if(err) {
                    handleError(err);
                    return false;
                }
                else {
                    console.log(res);
                    return true;
                }
            });
        }
    })
}




module.exports = db;