function validateEmail(email) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}

function validatePhoneNumber(phoneNumber) {
  const phoneRegex = /^(\+\d{1,3})?\d{9,12}$/;
  return phoneRegex.test(phoneNumber);
}

function validatePassword(password) {
  const passwordRegex = /^(?=.*\d)(?=.*[a-zA-Z])\S{5,}$/;
  return passwordRegex.test(password);
}

function signUpValidation(event) {
  const emailInput = document.getElementById("email").value;
  const phoneNumberInput = document.getElementById("phoneNumber").value;
  const passwordInput = document.getElementById("password").value;

  if (!validateEmail(emailInput)) {
    alert("Invalid email. Please enter a valid email address.");
    event.preventDefault(); // Prevent the default form submission
    return false;
  }

  if (!validatePhoneNumber(phoneNumberInput)) {
    alert("Invalid phone number. Please enter a valid phone number.");
    event.preventDefault();
    return false;
  }

  if (!validatePassword(passwordInput)) {
    alert(
      "Invalid password. Password must be at least 5 characters and contain a number."
    );
    event.preventDefault();
    return false;
  }

  // If all validations pass, you can proceed with form submission or other actions
  alert("Validation successful! Form submitted.");
  return true;
}
