document.addEventListener("DOMContentLoaded", function () {
    const icons = document.querySelectorAll(".match-icon");
    const letters = document.querySelectorAll(".match-letter");
    let selectedIcon = null;
    let selectedLetter = null;

    icons.forEach(icon => {
        icon.addEventListener("click", () => {
            if (selectedIcon) selectedIcon.classList.remove("selected");
            selectedIcon = icon;
            icon.classList.add("selected");

            // If a letter is already selected, check for a match
            if (selectedLetter) {
                checkMatch(selectedIcon, selectedLetter);
            }
        });
    });

    letters.forEach(letter => {
        letter.addEventListener("click", () => {
            if (selectedLetter) selectedLetter.classList.remove("selected");
            selectedLetter = letter;
            letter.classList.add("selected");

            // If an icon is already selected, check for a match
            if (selectedIcon) {
                checkMatch(selectedIcon, selectedLetter);
            }
        });
    });

    function checkMatch(icon, letter) {
        const iconId = icon.getAttribute("data-id");
        const iconLetter = icon.getAttribute("data-letter");
        const letterValue = letter.getAttribute("data-letter");

        if (iconLetter === letterValue) {
            // Correct match: add celebratory effect
            icon.classList.add("correct-match");
            letter.classList.add("correct-match");
            icon.classList.remove("selected");
            letter.classList.remove("selected");

            // Reset selections for next attempt
            selectedIcon = null;
            selectedLetter = null;

            // Check if the game is complete
            checkGameCompletion();
        } else {
            // Incorrect match: add shake effect
            icon.classList.add("wrong-match");
            letter.classList.add("wrong-match");

            setTimeout(() => {
                icon.classList.remove("wrong-match", "selected");
                letter.classList.remove("wrong-match", "selected");
                selectedIcon = null;
                selectedLetter = null;
            }, 500);
        }
    }

    function checkGameCompletion() {
        const allMatched = document.querySelectorAll(".correct-match").length === icons.length + letters.length;
        if (allMatched) {
            showCongratulationsModal();
        }
    }

    function showCongratulationsModal() {
        document.getElementById("congratulationsModal").style.display = "flex";
    }

    window.restartGame = function () {
        fetch("/matching-game/reset", { method: 'POST' })
            .then(() => location.reload());
    };
});



