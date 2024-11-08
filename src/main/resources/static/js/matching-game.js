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

            if (selectedIcon) {
                checkMatch(selectedIcon, selectedLetter);
            }
        });
    });

    function checkMatch(icon, letter) {
        const iconLetter = icon.getAttribute("data-letter");
        const letterValue = letter.getAttribute("data-letter");

        if (iconLetter === letterValue) {
            icon.classList.add("correct-match");
            letter.classList.add("correct-match");
            selectedIcon = null;
            selectedLetter = null;
            checkGameCompletion();
        } else {
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

    document.addEventListener('mousemove', function(e) {
        const sparkle = document.createElement('div');
        sparkle.className = 'sparkle';
        sparkle.style.left = `${e.pageX}px`;
        sparkle.style.top = `${e.pageY}px`;
        document.body.appendChild(sparkle);
        setTimeout(() => sparkle.remove(), 1000); // Removes the sparkle after animation
    });

});




