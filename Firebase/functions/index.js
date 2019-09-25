'use strict';

const functions = require('firebase-functions');
const cors = require('cors')({ origin: true });
const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);

exports.addWord = functions.https.onRequest((request, response) => {
	
	var vid = "";
	var vrus = "";
	var vtype = "";
	var vtat = "";
	
	cors(request, response, () => {
		vid = request.query.id;
		vrus = request.query.rus;
		vtype = request.query.type;
		vtat = request.query.tat;
	});
	
	admin.database().ref('/words/').push({id: vid, rus: vrus, tat: vtat, type: vtype}).then(snapshot => {
		// do nothing
	});
  
  
	response.send("OK");
});