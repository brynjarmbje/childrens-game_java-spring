const iconMapping = {
    'apple.png': 'fa-apple',
    'ant.png': 'fa-bug',
    'banana.png': 'fa-lemon', // Substitute close icons for unavailable ones
    'bird.png': 'fa-dove',
    'cat.png': 'fa-cat',
    'car.png': 'fa-car'
};

const iconData = [
    { id: 1, imageUrl: 'apple.png', letter: 'A' },
    { id: 2, imageUrl: 'ant.png', letter: 'A' },
    { id: 3, imageUrl: 'banana.png', letter: 'B' },
    { id: 4, imageUrl: 'bird.png', letter: 'B' },
    { id: 5, imageUrl: 'cat.png', letter: 'C' },
    { id: 6, imageUrl: 'car.png', letter: 'C' }
];

let selectedIcon = null;
let matchedCount = 0;

// Shuffle array function
function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

function initializeMatchGame() {
    const iconColumn = document.getElementById('icon-column');
    const letterColumn = document.getElementById('letter-column');

    matchedCount = 0; // Reset matched count
    selectedIcon = null; // Reset selected icon

    // Clear columns before rendering
    iconColumn.innerHTML = '';
    letterColumn.innerHTML = '';

    // Shuffle iconData for random order in both columns
    const shuffledIcons = [...iconData];
    const shuffledLetters = [...iconData];
    shuffleArray(shuffledIcons);
    shuffleArray(shuffledLetters);

    // Render shuffled icons on the left column
    shuffledIcons.forEach(icon => {
        const iconElement = document.createElement('div');
        iconElement.className = 'match-icon';
        iconElement.dataset.id = icon.id; // Set unique ID
        iconElement.innerHTML = `<i class="fas ${iconMapping[icon.imageUrl]}"></i>`;
        iconElement.onclick = () => selectIcon(icon, iconElement);
        iconColumn.appendChild(iconElement);
    });

    // Render shuffled letters on the right column
    shuffledLetters.forEach(icon => {
        const letterElement = document.createElement('div');
        letterElement.className = 'match-letter';
        letterElement.dataset.id = icon.id; // Set unique ID
        letterElement.textContent = icon.letter;
        letterElement.onclick = () => selectLetter(icon.letter, letterElement);
        letterColumn.appendChild(letterElement);
    });
}

// Select an icon for matching
function selectIcon(icon, iconElement) {
    selectedIcon = { icon, iconElement };
    clearSelections();
    highlightSelection(iconElement);
}

// Select a letter and check for match
function selectLetter(letter, letterElement) {
    if (!selectedIcon) return;

    // Check for match by comparing the IDs of the selected icon and letter
    if (selectedIcon.icon.letter === letter && selectedIcon.icon.id === parseInt(letterElement.dataset.id)) {
        // Match found - mark as matched
        markAsMatched(selectedIcon.iconElement, letterElement);
        matchedCount++; // Increment matched count

        // Check if all matches are found
        if (matchedCount === iconData.length) showCelebratoryModal();
        selectedIcon = null;
    } else {
        // No match - reset selection
        selectedIcon = null;
        clearSelections();
    }
}

// Highlight the selected element
function highlightSelection(element) {
    element.style.backgroundColor = '#e0e0e0';
}

// Clear any temporary highlights
function clearSelections() {
    document.querySelectorAll('.match-icon, .match-letter').forEach(el => {
        el.style.backgroundColor = '';
    });
}

// Mark both icon and letter as matched
function markAsMatched(iconElement, letterElement) {
    iconElement.classList.add('matched');
    letterElement.classList.add('matched');
}

// Show celebration modal when game is complete
function showCelebratoryModal() {
    const modal = document.getElementById('celebration-modal');
    modal.style.display = 'block';
}

// Reset the game
function startNewGame() {
    document.getElementById('celebration-modal').style.display = 'none';
    initializeMatchGame();
}
