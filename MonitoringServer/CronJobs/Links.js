const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/test3');

const Link = mongoose.model('Link', { id: String, bandwidth: [Number], packetloss: [Number], latency: [Number], jitter: [Number], throughput: [Number] });

let a = "5a71c64ca72c20235d090ca0"

// const first = new Link({ id: 'openflow:0:1'});
// first.bandwidth.push(1);
// first.save().then((res) => {
//   console.log('meow')
//   console.log(res._id)
//
// });



// Link.update({id:'openflow:4:1'}).then((res) => {
//
// console.log(res, "-------*******");
//   // res.bandwidth.push(9)
//   // res.update().then(() => console.log('meow2'));
//   // console.log(res, "-------");
// })


// first.bandwidth.push(89);
// first.save();

let id = 'openflow:2:3';
let band = 11;
//
// Link.findOneAndUpdate({id: id}, {$push: {bandwidth: band}}).then((res) => {
//   if (res == null) {
//     console.log("++++++++++++++++++++++++", "Entry does not exist");
//     return new Promise(function(resolve, reject) {
//       resolve({id:id, bandwidth: band});
//     });
//      //const first = new Link({ id: 'openflow:0:1'});
//      //first.bandwidth.push(11);
//   } else {
//     resolve(null);
//     console.log(res, " Entry exist, pushing done")
//   }
//
// }).then((res) => {
//   if(res != null) {
//     console.log("res is null");
//     const last = new Link({ id: res.id});
//     last.bandwidth.push(res.bandwidth)
//     last.save();
//   } else {
//     console.log("all done");
//   }
//
// });

Link.find().then((res) => {
  console.log(res, "-----------------------");


})
