var isHideJapan = true;
function showJapan() {
	if (isHideJapan) {
		document.getElementById('japan1').classList.remove("japan");
		document.getElementById('japan2').classList.remove("japan");
		document.getElementById('japan3').classList.remove("japan");
		document.getElementById('japan4').classList.remove("japan");
		isHideJapan = false;
		return;
	}
	if (!isHideJapan) {
		document.getElementById('japan1').classList.add("japan");
		document.getElementById('japan2').classList.add("japan");
		document.getElementById('japan3').classList.add("japan");
		document.getElementById('japan4').classList.add("japan");
		isHideJapan = true;
	}
}