// script.js

function validateEmail(email) {
  // Regular expression for a valid email format
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}

function validatePhoneNumber(phoneNumber) {
  // Regular expression for a valid phone number with optional country code
  const phoneRegex = /^(\+\d{1,3})?\d{9,12}$/; // Allowing an optional country code
  return phoneRegex.test(phoneNumber);
}

function validatePassword(password) {
  // Password must be at least 5 characters, have a number, and no spaces
  const passwordRegex = /^(?=.*\d)(?=.*[a-zA-Z])\S{5,}$/;
  return passwordRegex.test(password);
}

// Example of how to use these functions
function signUpValidation() {
  const emailInput = document.getElementById("emailInput").value;
  const phoneNumberInput = document.getElementById("phoneNumberInput").value;
  const passwordInput = document.getElementById("passwordInput").value;

  if (!validateEmail(emailInput)) {
    alert("Invalid email. Please enter a valid email address.");
    return false;
  }

  if (!validatePhoneNumber(phoneNumberInput)) {
    alert(
      "Invalid phone number. Please enter a valid phone number with or without a country code."
    );
    return false;
  }

  if (!validatePassword(passwordInput)) {
    alert(
      "Invalid password. Password must be at least 5 characters, contain a number, and have no spaces."
    );
    return false;
  }

  // If all validations pass, you can proceed with form submission or other actions
  // For example, you might want to submit the form data to a server here
  alert("Validation successful! Form submitted.");
  return true;
}

function openForgetPassword() {
  var forgetPasswordContainer = document.getElementById("forgetPassword");
  forgetPasswordContainer.style.left = "50%"; // Adjust this value as needed
  forgetPasswordContainer.style.display = "block";
}

function closeForgetPassword() {
  var forgetPassword = document.getElementById("forgetPassword");
  forgetPassword.style.left = "-510px"; // Move it off-screen to the left
}
/**
 * 
 */