var should = require('should'); 
var assert = require('assert');
var request = require('supertest');  
var mongoose = require('mongoose');
var winston = require('winston');
var moment = require('moment');

describe('Routing', function() {
  var url = 'http://104.236.131.132:3000';
  var employeeId;
  var eventId;

  // within before() you can run all the operations that are needed to setup your tests. In this case
  // I want to create a connection with the database, and when I'm done, I call done().
  before(function(done) {
    // In our tests we use the test db
    mongoose.connect("mongodb://104.236.131.132/mlb");              
    done();
  });
  describe('Employee Tests', function() {
    it('Should Return JSON of All Employees', function(done) {
    request(url)
	  .get('/employee/getAllEmployees')
    .end(function(err, res) {
        if (err || !res || !err && !res) {
          throw err;
        } else {
          employeeId = res.body.employees[0].employee._id;
          res.should.be.json;
          res.status.should.be.equal(200);
        }
        done();
      });
    });
    
      it('Get Availability Calendar', function(done) {
      var urlPath = '/employee/getAvailabilityCalendar?employee='+employeeId+'&end=2017-04-02T00:00:00.000Z&start=2017-03-26T00:00:00.000Z';
      request(url)
      .get(urlPath)
      .expect(200)
      .end(function(err, res) {
        eventId = res.body[0]._id;
          if (err || !res || !err && !res) {
            throw err;
          } else {
            res.should.be.json;
          }
         done();
      });
    });


    //Needs Angular Full Calendar Event Object
    // it('Set Available Time', function(done) {
    // var body = {title: 'Available Time', event: { start: "2017-04-01T18:20:09.758Z" , end: "2017-04-01T23:18:09.758Z" }, employee_id: "58d7f7e2af1906477c176870"};
    // request(url)
    // .post('/employee/setAvailableTime')
    // .send(body)
    // .expect(200)
    // .end(function(err, res) {
    //     if (err) {
    //       throw err;
    //     }   
    //     done();
    //   });
    // });

    it('Delete Available Time', function(done) {
        var body = {id: eventId};
        request(url)
        .delete('/employee/deleteAvailableTime')
        .send(body)
        .expect(200)
        .end(function(err, res) {
          if (err || !res || !err && !res) {
            throw err;
          } 
          done();
        });
      });


    it('Get Employee By ID', function(done) {
      var urlPath = '/employee/getEmployeeById?id='+employeeId;
      request(url)
      .get(urlPath)
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


  });
});