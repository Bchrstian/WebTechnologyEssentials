<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="style.css" />
    <script src="script.js"></script>
    <script src="validation.js"></script>
    <title>Welcome to Student Portal</title>
  </head>
  <body>
    <div class="welcome-page">
      <h1>Welcome to the Student Portal</h1>
      <p>
        We're thrilled to have you on board! Join us in your educational
        journey.
      </p>
      <p>Click below to get started:</p>
      <div class="welcome-buttons">
        <a href="#" class="btn" onclick="openSignUpModal()">Sign Up</a>
        <a href="#" class="btn" onclick="openLogin()">Log In</a>
      </div>
      <p class="change-language" onclick="changeLanguage()">Change Language</p>
    </div>

    <!-- Sign Up Modal -->
    <div class="modal" id="signUpModal">
      <div class="modal-content sign-up-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>Sign Up</h2>
        <form action="SignUpServlet" method="post" onsubmit="return signUpValidation(event)">
        
          <div class="input-box">
            <input
              type="text"
              id="firstName"
              name="firstName"
              class="input-field"
              placeholder="First Name"
            />
            <i class="bx bx-user"></i>
          </div>
          <div class="input-box">
            <input
              type="text"
              id="lastName"
              name="lastName"
              class="input-field"
              placeholder="Last Name"
            />
            <i class="bx bx-user"></i>
          </div>
          <div class="input-box">
            <input
              type="email"
              id="email"
              name="email"
              class="input-field"
              placeholder="Email"
            />
            <i class="bx bx-envelope"></i>
          </div>
          <div class="input-box">
            <input
              type="tel"
              id="phoneNumber"
              name="phoneNumber"
              class="input-field"
              placeholder="Phone Number"
            />
            <i class="bx bx-phone"></i>
          </div>
          <div class="input-box">
            <input
              type="password"
              id="password"
              name="password"
              class="input-field"
              placeholder="Password"
            />
            <i class="bx bx-lock-alt"></i>
          </div>
          <div class="input-box">
            <label for="department">Choose Department:</label>
            <select id="department" name="department" required>
              <option value="">Please choose your department:</option>
              <option value="Software Engineering">Software Engineering</option>
              <option value="Networking">Networking</option>
              <option value="Information Technology">
                Information Technology
              </option>
              <option value="Cyber security">Cyber Security</option>
            </select>
          </div>
          <div class="input-box">
            <input type="submit" class="submit" value="Sign Up" />
          </div>
        </form>
        <p>
          Already have an account? <a href="#" onclick="openLogin()">Log In</a>
        </p>
      </div>
    </div>

    <!-- Login Modal -->
 <div class="modal" id="loginModal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>Login</h2>
        <form action="LoginServlet" method="post">
            <div class="input-box">
                <input type="text" name="email" class="input-field" placeholder="Username or Email" />
                <i class="bx bx-user"></i>
            </div>
            <div class="input-box">
                <input type="password" name="password" class="input-field" placeholder="Password" />
                <i class="bx bx-lock-alt"></i>
            </div>
            <div class="input-box">
                <input type="submit" class="submit" value="Log In" />
            </div>
        </form>
        <p>
            Don't have an account?
            <a href="#" onclick="openSignUpModal()">Sign Up</a>
        </p>
    </div>
</div>
    <footer>
      <p class="copyright">
        Copyright &copy; 2024 by B Christian, Inc. All rights reserved.
      </p>
    </footer>
    <script src="script.js"></script>
  </body>
</html>
