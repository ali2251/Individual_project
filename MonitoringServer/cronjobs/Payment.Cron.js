var Employee = require('../models/Employee');
var Employer = require('../models/Employer');
var async = require('async');
var CronJob = require('cron').CronJob;
var mongoose = require('mongoose');
var moment = require('moment');
var Booking = require('../models/Booking');
mongoose.connect('mongodb://localhost/mlb');


//Cron Job for Bookings
new CronJob('* * * * * *', function() {
  Booking
  .find({
   end: {
     $gte: moment().add(2, 'days'),
     $lt: moment().add(3, 'days')
    }
  })
  .populate('employee')
  .populate('employer')
  .exec(function(err, list){
    console.log(list);

    async.eachLimit(list, 5, function(booking){
      if(booking.paymentStatus == "Taken") {
        stripe.charges.create({
          amount: booking.price,
          currency: "gbp",
          source: booking.paymentToken,
          application_fee: (0.08 * booking.price) + 30,
        }, {
          stripe_account: booking.employee.bankAccount,
        }).then(function(charge) {
          var body = {paymentStatus: "Paid"};
          Booking.findByIdAndUpdate(booking._id, body, {new: true}, function(err, model) {
          });
        });
      }
    });
  });
}, null, true, 'America/Los_Angeles');
