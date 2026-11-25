// Dark Mode
document.getElementById('darkBtn').addEventListener('click', function() {
    document.body.classList.toggle('dark');
    this.textContent = document.body.classList.contains('dark') ? 'Light Mode' : 'Dark Mode';
});

// Search
let searchInput = document.getElementById('searchInput');
if (searchInput) {
    searchInput.addEventListener('keyup', function() {
        let value = this.value.toLowerCase();
        document.querySelectorAll('.dest-card').forEach(card => {
            let title = card.querySelector('h3').textContent.toLowerCase();
            card.style.display = title.includes(value) ? 'block' : 'none';
        });
    });
}

// Filter
document.querySelectorAll('.filter-btn').forEach(btn => {
    btn.addEventListener('click', function() {
        let filter = this.getAttribute('data-filter');
        document.querySelectorAll('.dest-card').forEach(card => {
            let cat = card.getAttribute('data-category');
            card.style.display = (filter === 'all' || cat === filter) ? 'block' : 'none';
        });
    });
});

// Info Box + Accordion
function showInfo(name) {
    document.getElementById('infoBox').textContent = 'You clicked: ' + name + ' - Book your trip on the Contact page!';
}

function toggleFaq(id) {
    document.getElementById(id).classList.toggle('hidden');
}

// Gallery
document.querySelectorAll('.gallery-img').forEach(img => {
    img.addEventListener('click', () => {
        let preview = document.getElementById('previewImg');
        if (preview) {
            preview.src = img.src;
            preview.classList.remove('hidden');
        }
    });
});

// Form Validation + LocalStorage
let bookingForm = document.getElementById('bookingForm');
if (bookingForm) {
    let saved = localStorage.getItem('lastBooking');
    if (saved) {
        let box = document.getElementById('savedBooking');
        box.classList.remove('hidden');
        box.textContent = 'Welcome back! Your last booking: ' + saved;
    }

    bookingForm.addEventListener('submit', (e) => {
        e.preventDefault();
        let name = document.getElementById('fullName').value.trim();
        let email = document.getElementById('email').value.trim();
        let phone = document.getElementById('phone').value.trim();
        let isValid = true;

        if (name.length < 3) {
            document.getElementById('nameError').textContent = 'Name must be at least 3 letters';
            isValid = false;
        } else {
            document.getElementById('nameError').textContent = '';
        }

        if (!email.includes('@')) {
            document.getElementById('emailError').textContent = 'Enter a valid email';
            isValid = false;
        } else {
            document.getElementById('emailError').textContent = '';
        }

        if (phone.length < 8) {
            document.getElementById('phoneError').textContent = 'Phone must be 8 digits';
            isValid = false;
        } else {
            document.getElementById('phoneError').textContent = '';
        }

        if (isValid) {
            let dest = document.getElementById('destination').value;
            localStorage.setItem('visitorName', name);
            localStorage.setItem('lastBooking', name + ' - ' + dest);
            document.getElementById('successMsg').classList.remove('hidden');
            bookingForm.reset();
        }
    });
}