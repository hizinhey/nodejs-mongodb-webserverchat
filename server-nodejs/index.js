require('dotenv').config();

var express = require("express");
var app     = express();
var server  = require("http").createServer(app);
var io      = require("socket.io").listen(server);
var fs      = require("fs");

var db      = require("./db-utils");

server.listen(process.env.PORT || 3000);

// listen connection-event
// id != 1
io.sockets.on('connection', (socket) => {
  let _id;

  socket.on('join-room', (id_user)=>{
    _id = id_user; 
     socket.join(_id);
     console.log("Chao mung id: "+ _id +" da ket noi.");
  })


  // listen send-message-event
  // input: _id, _mess, _send
  socket.on('send-message-event', (_mess, _send)=>{
    db.addMess(_id, _mess, _send);
    io.sockets.in(_id).on('get-message-event', () =>{
      socket.emit('client-get-mess', getMess(_id));
    })
  })
  
  // listen get-message-event
  // input: _id (!= 1)
  if(_id != 1){
    socket.on('get-message-event', () =>{
      socket.emit('client-get-mess', getMess(_id));
    })  
  }

  // listen get-list-user-event
  // input: void
  socket.on('get-list-user-event', ()=> {
    socket.emit('client-get-list', getList());
  })
  
})


// function get mess to json. input _id != 1
let getMess = (_id) => {
  return db.findMess(_id)
}

// function get list to json
let getList = () => {
  return db.findUsers();
}


// connect
// send mess
// get mess
// get list
