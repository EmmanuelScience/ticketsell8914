function toggle_button(id) {
    const btn = document.getElementById(id);
    if (btn.classList.contains("buttonNotClicked")) {
        btn.classList.remove("buttonNotClicked")
        btn.classList.add("buttonClicked")
        btn.innerHTML = "true"
    } else {
        btn.classList.remove("buttonClicked")
        btn.classList.add("buttonNotClicked")
        btn.innerHTML = "true"
    }
}

