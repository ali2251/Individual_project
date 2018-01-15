var should = require('should'); 
var assert = require('assert');
var request = require('supertest');  
var mongoose = require('mongoose');
var winston = require('winston');

describe('Routing', function() {
  var url = 'http://104.236.131.132:3000';
  var advertId;
  var employeeId;

  // within before() you can run all the operations that are needed to setup your tests. In this case
  // I want to create a connection with the database, and when I'm done, I call done().
  before(function(done) {
    // In our tests we use the test db
    mongoose.connect("mongodb://104.236.131.132/mlb");							
    done();
  });


  describe('Advert Tests', function() {
    it('Create Advert', function(done) {
   	var body =  {employer_id:"58bd988c4c9ceb3aa5733f43", start: new Date(), summary: "hello", specialties: "beer", end: new Date(), address: "55 Upper", postcode: "SE19EY", eventType: "Private", wage: 25};
    request(url)
    .post('/advert/createAdvert')
    .send(body)
    .expect(200)
    .end(function(err, res) {
        if (err || !res || !err && !res) {
          throw err;
        } else {
        	advertId = res.body.advert._id;
        	res.should.be.json;
        }   

        done();
      });
    });

    it('Get All Adverts', function(done) {
    request(url)
    .get('/advert/getAllAdverts')
    .expect(200)
    .end(function(err, res) {
        if (err || !res || !err && !res) {
          throw err;
        } else {      
        	res.should.be.json;
        	res.should.be.array;
        }
        // this is should.js syntax, very clear
        done();
      });
    });

    it('Get Adverts for Employer', function(done) {
   	var body = {employer_id: '58d7f6f1af1906477c17686c'};
    request(url)
    .post('/advert/getAdvertsForEmployer')
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

    it('Get Adverts By Id', function(done) {
    var body = {advert_id: advertId};
    request(url)
    .post('/advert/getAdvertsById')
    .send(body)
    .expect(200)
    .end(function(err, res) {
        if (err || !res || !err && !res) {
          throw err;
        } else {
        	res.should.have.body;
        	res.should.be.json;
        }

        // this is should.js syntax, very clear
        done();
      });
    });


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

    it('Get Adverts For Employee', function(done) {
  	var body = {employee_id: employeeId};
    request(url)
    .get('/advert/getAdvertsForEmployee')
    .send(body)
    .expect(200)
    .end(function(err, res) {
        if (err || !res || !err && !res) {
          throw err;
        } else {
  			res.should.be.json;
        	res.status.should.be.equal(200);  
        }
        done();
      });
    });



    it('Update Advert', function(done) {
    var body = {advert_id: advertId, postcode: "SE17SR"};
    request(url)
    .post('/advert/updateAdvert')
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

    it('Delete Advert', function(done) {
    var body = {advert_id: advertId};
    request(url)
    .delete('/advert/deleteAdvert')
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