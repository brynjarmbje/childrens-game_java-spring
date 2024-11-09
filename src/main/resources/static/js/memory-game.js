document.addEventListener("DOMContentLoaded", function () {
    const board = document.getElementById("memoryGameBoard");

    // Flip card on click
    board.addEventListener("click", function (event) {
        const button = event.target.closest("button");
        if (!button || button.classList.contains("matched")) return;

        const cardId = button.getAttribute("data-id");
        fetch(`/memory-game/flip?id=${cardId}`, { method: 'POST' })
            .then(response => response.json())
            .then(data => updateBoard(data.cards));
    });

    function updateBoard(cards) {
        board.innerHTML = ""; // Clear board
        cards.forEach(card => {
            const cardEl = document.createElement("button");
            cardEl.setAttribute("data-id", card.id);
            cardEl.className = card.matched ? "matched" : card.flipped ? "flip" : "";
            cardEl.innerHTML = card.flipped ? card.letter : `<i class="fas ${card.imageUrl}"></i>`;
            board.appendChild(cardEl);
        });

        if (cards.every(card => card.matched)) showCongratulationsModal();
    }

    function showCongratulationsModal() {
        document.getElementById("congratulationsModal").style.display = "flex";
    }

    window.restartGame = function () {
        fetch("/memory-game/reset", { method: 'POST' })
            .then(() => location.reload());
    };
});

