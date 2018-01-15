var should = require('should'); 
var assert = require('assert');
var request = require('supertest');  
var mongoose = require('mongoose');
var winston = require('winston');

describe('Routing', function() {
  var url = 'http://104.236.131.132:3000';

  // within before() you can run all the operations that are needed to setup your tests. In this case
  // I want to create a connection with the database, and when I'm done, I call done().
  before(function(done) {
    // In our tests we use the test db
    mongoose.connect("mongodb://104.236.131.132/mlb");              
    done();
  });
  describe('Payment Tests', function() {
      it('Create charge', function(done) {
      var body = {
        amount:1000,
        sender:"cus_AMNM90zBSGAthT",
        recipient: "acct_1A1flXCTEZoCLhJu"
      };
      request(url)
    	.post('/payment/charge')
      .expect(200)
    	.send(body)
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