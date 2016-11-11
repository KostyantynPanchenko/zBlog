function validateLogin(event) {
	var event = event || window.event; // for lt IE9
	var userName = document.getElementById("userName").value;
	var password = document.getElementById("password").value;

	if (userName == "" || password == "") {
		if (event.preventDefault)
			event.preventDefault(); // Standard technique
		if (event.returnValue)
			event.returnValue = false; // IE
		document.getElementById("error").innerHTML = "All fields are reqired!";
		document.getElementById("error").style.color = "red";
	}
}

function validateRegistration(event) {
	var event = event || window.event; // for lt IE9
	var userName = document.getElementById("userName").value;
	var mail = document.getElementById("mail").value;
	var password = document.getElementById("password").value;
	var repeat = document.getElementById("repeat").value;

	if (userName == "" || password == "" || mail == "" || repeat == "") {
		if (event.preventDefault)
			event.preventDefault(); // Standard technique
		if (event.returnValue)
			event.returnValue = false; // IE
		document.getElementById("error").innerHTML = "All fields are reqired!";
	}

	if (password != repeat) {
		if (event.preventDefault)
			event.preventDefault(); // Standard technique
		if (event.returnValue)
			event.returnValue = false; // IE
		document.getElementById("error").innerHTML = "Your passwords does not match! Please, enter valid password";
		document.getElementById("userName").value = userName;
		document.getElementById("mail").value = mail;
	}
}