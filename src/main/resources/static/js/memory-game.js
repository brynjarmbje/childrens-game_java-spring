// Toggle display for the memory game section
function toggleMemoryGame() {
    const memoryGameContainer = document.getElementById('memoryGameContainer');
    memoryGameContainer.style.display = memoryGameContainer.style.display === 'none' ? 'block' : 'none';
}

// Initialize and render the memory game
async function initializeMemoryGame() {
    const response = await fetch('/game/initialize');
    const cards = await response.json();
    renderMemoryBoard(cards);
}

async function flipMemoryCard(id) {
    const response = await fetch(`/game/flip/${id}`, { method: 'POST' });
    const message = await response.text();
    alert(message);
    loadMemoryCards();
}

async function loadMemoryCards() {
    const response = await fetch('/game/cards');
    const cards = await response.json();
    renderMemoryBoard(cards);
}

const iconMapping = {
    'apple.png': 'fa-apple',
    'ant.png': 'fa-bug',
    'banana.png': 'fa-lemon', // Substitute close icons for unavailable ones
    'bird.png': 'fa-dove',
    'cat.png': 'fa-cat',
    'car.png': 'fa-car'
};

function renderMemoryBoard(cards) {
    const board = document.getElementById('memory-game-board');
    board.innerHTML = '';  // Clear the board before rendering

    cards.forEach(card => {
        const cardElement = document.createElement('div');
        cardElement.className = 'memory-card';

        // Show the icon if the card is not flipped; otherwise, show the letter
        if (!card.flipped) {
            cardElement.innerHTML = `<i class="fas ${iconMapping[card.imageUrl]}"></i>`;
        } else {
            cardElement.textContent = card.letter;
        }

        // Add flip functionality
        cardElement.onclick = () => flipMemoryCard(card.id);
        board.appendChild(cardElement);
    });
}