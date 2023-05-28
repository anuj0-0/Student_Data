console.log("hi");
var xhr;
var totalStudentsCount;
function send(requestType, endPoint, data, resFunction) {
	xhr = new XMLHttpRequest();
	xhr.open(requestType, endPoint, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				resFunction(xhr);
			} else {
				console.error("Error: " + xhr.status);
			}
		}
	};
	if (data !== null) {
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send(JSON.stringify(data));
	}
	else
		xhr.send();
}

function addStudent() {
	const form = document.querySelector("form");
	const data = new FormData(form);
	let jsData = Object.fromEntries(data.entries());
	console.log(jsData);

	send("POST", "/students/save", jsData, pagination);
	window.alert("Student details Saved");
}

function pagination() {

	const form = document.querySelector("#page");
	const data = new FormData(form);
	let jsData = Object.fromEntries(data.entries());
	console.log(jsData);
	send("POST", "/students/page", jsData, fillTable);
	document.querySelector("#filterInput").value = "";
}

function deleteStudent(e, id) {
	e.preventDefault();
	send("DELETE", "/students/" + id, null, pagination);
}

function fillTable(xhr) {

	let students = JSON.parse(xhr.responseText);
	let tableString = "<thead><tr><th>Id</th><th>Name</th><th>Gender</th><th>Total Marks</th><th>Action</th></tr></thead><tbody>";
	for (let i = 0; i < students.length && i < 10; i++) {
		tableString += "<tr><td>" + students[i].id + "</td><td>" + students[i].name + "</td><td>" + students[i].gender + "</td><td>" + students[i].marks + "</td>";
		tableString += "<td><a onclick=\"deleteStudent(event," + students[i].id + ")\" href=\"\">Delete</a></td></tr>";
		lastStudentId = students[i].id;
	}
	tableString += "</tbody>";
	let table = document.querySelector("#myTable");
	table.innerHTML = tableString;
	table.style.visibility = "visible";

}

function createPageNoDropDown(studentsCount) {
	let paginationString = "";
	let totalPage = calculatePageCount(studentsCount);
	let select = document.querySelector(".select");

	for (let i = 1; i <= totalPage; i++) {
		paginationString += "<option value=" + i + ">" + i + "</option>";
	}
	select.innerHTML = paginationString;
	select.style.visibility = "visible";
}

$(document).ready(function() {
	// Filter table rows based on input value
	$('#filterInput').on('keyup', function() {
		var value = $(this).val().toLowerCase();
		$('#myTable tbody tr').filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
		});
	});
});

function getAllStudents(fun) {
	send("GET", "/students/all", null, fun);
}

function calculatePageCount(totalStudentsCount) {
	let totalPage = Math.floor(totalStudentsCount / 10);
	let remPage = totalStudentsCount % 10;
	if (remPage > 0) {
		totalPage += 1;
	}
	return totalPage;
}
function fillTotalStudentsCount(xhr) {
	totalStudentsCount = xhr.responseText;
	getAllStudents(fillTable);
	createPageNoDropDown(totalStudentsCount);
}

let button = document.getElementById("button");
button.addEventListener("click", addStudent);

let option = document.querySelector("select");
option.addEventListener("change", pagination);


send("GET", "/students/count", null, fillTotalStudentsCount);
