<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*, javax.servlet.http.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="admission.css">
    <title>Student Admission</title>
</head>
<body>
<div class="admission-container">
    <div class="profile-section">
        <img src="default-avatar.png" alt="Default Profile Picture" class="profile-picture" />

        <%-- Retrieve student name and department from the session --%>
        <%
            HttpSession userSession = request.getSession(true);
            if (userSession != null) {
                String firstName = (String) userSession.getAttribute("firstName");
                String lastName = (String) userSession.getAttribute("lastName");
                String department = (String) userSession.getAttribute("department");

                if (firstName != null && lastName != null && department != null) {
                    String studentName = firstName + " " + lastName;
        %>
        <h1>Welcome, <%= studentName %>!</h1>
        <p>
            You have successfully registered for the academic year 2024-2025 in
            the <%= department %> department.
        </p>
        <%
                }
            }
        %>
    </div>

    <div class="form-section">
        <form action="AdmissionServlet" method="post" enctype="multipart/form-data">
            <!-- Passport Picture Upload -->
            <div class="upload-section">
                <label for="passport">Upload Passport Picture (jpg or png):</label>
                <input
                        type="file"
                        id="passport"
                        name="passport"
                        accept=".jpg, .png"
                />
            </div>

            <!-- Certificates (Diploma) Upload -->
            <div class="upload-section">
                <label for="certificates">Upload Certificates (PDF only):</label>
                <input
                        type="file"
                        id="certificates"
                        name="certificates"
                        accept=".pdf"
                />
            </div>

            <!-- Submit Button -->
            <button type="submit" name="submitAdmission">Submit Admission</button>
        </form>
    </div>
</div>
</body>
</html>
