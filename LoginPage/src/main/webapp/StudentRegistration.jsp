<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="style.css" />
    <script src="script.js"></script>
    <title>Christian | Login & Registration</title>
  </head>
  <body>
    <div class="wrapper">
      <nav class="nav">
        <div class="nav-menu" id="navMenu">
          <ul>
            <li><a href="#" class="link active">Home</a></li>
            <li><a href="#" class="link">Blog</a></li>
            <li><a href="#" class="link">Services</a></li>
            <li><a href="#" class="link">About</a></li>
          </ul>
        </div>
        <div class="nav-button">
          <button class="btn" id="registerBtn" onclick="register()">
            Sign Up
          </button>
          <button class="btn white-btn" id="loginBtn" onclick="login()">
            Sign In
          </button>
        </div>
        <div class="nav-menu-btn">
          <i class="bx bx-menu" onclick="myMenuFunction()"></i>
        </div>
      </nav>

      <!----------------------------- Form box ----------------------------------->
      <div class="form-box">
        <!------------------- login form -------------------------->

        <div class="login-container" id="login">
          <div class="top">
            <span
              >Don't have an account?
              <a href="#" onclick="register()">Sign Up</a></span
            >
            <header>Login</header>
          </div>
          
          <form action="LoginServlet" method="post"> 
          <div class="input-box">
            <input
              type="text"
              class="input-field"
             name="email"
              placeholder="Username or Email"
            />
            <i class="bx bx-user"></i>
          </div>
          <div class="input-box">
            <input type="password" class="input-field" name="password" placeholder="Password" />
            <i class="bx bx-lock-alt"></i>
          </div>
          <div class="input-box">
           <input type="submit" class="submit" value="Sign In" />
          </div>
          </form>
          <div class="two-col">
            <div class="one">
              <input type="checkbox" id="login-check" />
              <label for="login-check"> Remember Me</label>
            </div>
            <div class="two">
              <label
                ><a href="#" onclick="openForgetPassword()"
                  >Forgot password?</a
                ></label
              >
            </div>
          </div>
        </div>

        <!------------------- registration form -------------------------->
        <div class="register-container" id="register">
          <div class="top">
            <span
              >Have an account? <a href="#" onclick="login()">Login</a></span
            >
            <header>Sign Up</header>
          </div>
        <form action="RegisterServlet" method="post"> 
    <div class="two-forms">
        <div class="input-box">
            <input type="text" class="input-field" name="firstname" placeholder="Firstname" />
            <i class="bx bx-user"></i>
        </div>
        <div class="input-box">
            <input type="text" class="input-field" name="lastname" placeholder="Lastname" />
            <i class="bx bx-user"></i>
        </div>
    </div>
    <div class="input-box">
        <input type="text" class="input-field" id="emailInput" name="email" placeholder="Email" />
        <i class="bx bx-envelope"></i>
    </div>
    <div class="input-box">
        <input type="text" class="input-field" id="phoneNumberInput" name="phoneNumber" placeholder="Phone Number" />
        <i class="bx bx-phone"></i>
    </div>
    <div class="input-box">
        <input type="password" class="input-field" id="passwordInput" name="password" placeholder="Password" />
        <i class="bx bx-lock-alt"></i>
    </div>
    <div class="input-box">
        <input type="submit" class="submit" value="Register" onclick="signUpValidation()" />
    </div>
</form>
        
          <div class="two-col">
            <div class="one">
              <input type="checkbox" id="register-check" />
              <label for="register-check"> Remember Me</label>
            </div>
            <div class="two">
              <label><a href="#">Terms & conditions</a></label>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Forget Password Page -->
    <div class="forget-password-container" id="forgetPassword">
      <div class="top">
        <header>Forgot Password</header>
      </div>
      <form action="ForgotPasswordServlet" method="post">
      <div class="input-box">
        <input
          type="text"
          class="input-field"
          id="forgetEmailInput"
          placeholder="Email"
          name="forgetEmailInput"
        />
        <i class="bx bx-envelope"></i>
      </div>
      <div class="input-box">
        <input
          type="password"
          class="input-field"
          id="newPasswordInput"
          placeholder="New Password"
          name="newPasswordInput"
        />
        <i class="bx bx-lock-alt"></i>
      </div>
      <div class="input-box">
        <input
          type="submit"
          class="submit"
          value="Forget Password"
          onclick="sendVerificationCode()"
        />
      </div>
       </form>
    </div>

    <!-- Verification Code Page -->
    <div class="verification-code-container" id="verificationCodePage">
      <div class="top">
        <header>Verification Code</header>
      </div>
      <div class="input-box">
        <input
          type="text"
          class="input-field"
          id="verificationCodeInput"
          placeholder="Verification Code"
        />
      </div>
      <div class="input-box">
        <input
          type="submit"
          class="submit"
          value="Submit"
          onclick="verifyCode()"
        />
      </div>
    </div>

    <script>
      // Function to send verification code
      function sendVerificationCode() {
        // Basic validation
        var forgetEmailInput =
          document.getElementById("forgetEmailInput").value;
        var newPasswordInput =
          document.getElementById("newPasswordInput").value;

        if (
          !validateEmail(forgetEmailInput) ||
          newPasswordInput.trim() === ""
        ) {
          alert("Please enter a valid email and a new password.");
          return;
        }

        // Add logic to send the code to the user's email or phone number
        // After sending the code, hide the Forget Password section and show the Verification Code section
        document.getElementById("forgetPassword").style.display = "none";
        document.getElementById("verificationCodePage").style.display = "block";
      }

      // Function to verify the entered code
      function verifyCode() {
        // Add logic to check if the entered code is correct
        // If correct, show success message and redirect to the Sign In page
        alert("Password changed successfully!");
        window.location.href = "#"; // Redirect to the Sign In page
      }

      // Function to validate email format
      function validateEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
      }

      function myMenuFunction() {
        var i = document.getElementById("navMenu");

        if (i.className === "nav-menu") {
          i.className += " responsive";
        } else {
          i.className = "nav-menu";
        }
      }

      var a = document.getElementById("loginBtn");
      var b = document.getElementById("registerBtn");
      var x = document.getElementById("login");
      var y = document.getElementById("register");

      function login() {
        x.style.left = "4px";
        y.style.right = "-520px";
        a.className += " white-btn";
        b.className = "btn";
        x.style.opacity = 1;
        y.style.opacity = 0;
      }

      function register() {
        x.style.left = "-510px";
        y.style.right = "5px";
        a.className = "btn";
        b.className += " white-btn";
        x.style.opacity = 0;
        y.style.opacity = 1;
      }
    </script>
  </body>
</html>