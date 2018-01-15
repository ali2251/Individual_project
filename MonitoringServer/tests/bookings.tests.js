var should = require('should'); 
var assert = require('assert');
var request = require('supertest');  
var mongoose = require('mongoose');
var winston = require('winston');

describe('Routing', function() {
  var url = 'http://104.236.131.132:3000';
  var employeeId;
  var bookingId;

  // within before() you can run all the operations that are needed to setup your tests. In this case
  // I want to create a connection with the database, and when I'm done, I call done().
  before(function(done) {
    // In our tests we use the test db
    mongoose.connect("mongodb://104.236.131.132/mlb");              
    done();
  });
  describe('Bookings', function() {
    it('Should Return JSON of All Employees', function(done) {
    request(url)
    .get('/employee/getAllEmployees')
    .end(function(err, res) {
        if (err || !res || !err && !res) {
          throw err;
        } else {
          employeeId = res.body.employees[0]._id;
          res.status.should.be.equal(200);
        }
        // this is should.js syntax, very clear
        done();
      });
    });

    it('Should Create Booking Between Two Users', function(done) {
    var body = {employer_id: '58d82fd2767c2348cc25c17e', employee_id: "58d7f76caf1906477c17686e", price:100, start: new Date(), end:new Date(), paymentToken:'cus_AMUvEB36fb9IzG', price: 1000, location: '55 Upper Ground, London'};
    request(url)
    .post('/booking/create')
    .send(body)
    .expect(200)
  	.end(function(err, res) {
        if (err || !res || !err && !res) {
          throw err;
        } else {
          bookingId = res.body.booking._id;
          res.should.be.json;
        }
        done();
      });
    });

    it('Delete Booking', function(done) {
    var body = {booking_id: bookingId};
    request(url)
    .delete('/booking/delete')
    .send(body)
    .expect(200)
    .end(function(err, res) {
        if (err || !res || !err && !res) {
          throw err;
        } else {
          res.should.be.json;
        }
        done();
      });
    });

    it('Get Bookings for Employee', function(done) {
    var body = {employee_id: "58d7f76caf1906477c17686e"};
    request(url)
    .post('/booking/employee')
    .send(body)
    .expect(200)
    .end(function(err, res) {
        if (err || !res || !err && !res) {
          throw err;
        } else {
          res.should.be.array;
        }
        done();
      });
    });


    it('Get Bookings for Employer', function(done) {
    var body = {employer_id: '58d7f6f1af1906477c17686c'};
    request(url)
    .post('/booking/employer')
    .send(body)
    .expect(200)
    .end(function(err, res) {
        if (err || !res || !err && !res) {
          throw err;
        } else {
          res.should.be.array;
        }
        done();
      });
    });

    it('Update Booking', function(done) {
    var body = {booking_id: bookingId, location: "55 Upper Ground"};
    request(url)
    .post('/booking/update')
    .send(body)
    .expect(200)
    .end(function(err, res) {
        if (err || !res || !err && !res) {
          throw err;
        } else {
          res.should.be.json;
        }
          // this is should.js syntax, very clear
        done();
      });
    });

  });
});

