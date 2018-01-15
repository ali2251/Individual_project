var mongoose = require('mongoose');
var bcrypt   = require('bcryptjs');
var Schema = mongoose.Schema;

var userSchema = mongoose.Schema({
    email : {
      type: String
    },
    gender: {
      type: ["Male", "Female"]
    },
    password : {
      type: String
    },
    name: {
        type: String,
        trim: true,
        required: true
    },
    photoUrl: {
      type : String
    },
    stripeToken: {
      type : String
    },
    dob: {
      type:String
    },
    phoneNumber : {
      type: String
    },
    chats: [String],
    address: {
      type: String, 
      required: true
    },
    postCode: {
      type: String, 
      required: true
    },
    latitude: {
      type: String
    },
    longitude: {
      type: String
    },
    rating: {
        type: Number,
	     default: 10,
        enum : [1,2,3,4,5,6,7,8,9,10]
    },
    radius: {
        type: Number
    },
    role: {
        type: String,
        enum: ['user', 'employee', 'employer', 'admin'],
        default: ['user']
    }
});


module.exports = mongoose.model('User', userSchema);


module.exports.createUser = function(newUser, callback){
    bcrypt.genSalt(10, function(err, salt) {
        bcrypt.hash(newUser.password, salt, function(err, hash) {
            newUser.password = hash;
            newUser.save(callback);
        });
    });
}

module.exports.comparePassword = function(candidatePassword, hash, callback){
    bcrypt.compare(candidatePassword, hash, function(err, isMatch) {
        if(err) {
          console.log("\n\n\n\n\n  ERROR THROWN FROM comparePassword \n\n\n\n\n ");
        }
        callback(null, isMatch);
    });
}

module.exports.getUserByEmail = function(email, callback) {
    var query = {email: email};
    User.findOne(query, callback);

}

