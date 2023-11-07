<%-- 
    Document   : viewInformation
    Created on : Nov 1, 2023, 12:56:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #6b90da; /* Blue background color */
            text-align: center;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }

        .container {
            max-width: 600px;
            background-color: #fff; /* White background color */
            padding: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            width: 100%;
        }

        h1 {
            color: #333; /* Black text color */
        }

        img {
            max-width: 250px; /* Adjust maximum width */
            max-height: 200px; /* Adjust maximum height */
            border-radius: 50%;
            margin: 12px 0;
        }

        p {
            font-size: 18px;
            color: #555; /* Gray text color */
        }

        strong {
            font-weight: bold;
            color: #333; /* Black text color */
        }

        /* Style for the email link */
        a {
            text-decoration: none;
            color: #0078d4; /* Blue text color */
            transition: color 0.3s;
        }

        a:hover {
            color: #0056b3; /* Hover color for the link */
        }
    </style>
    </head>
    <body>
        <div class="container">
        <h1>Instructor Information</h1>

        <img src="${requestScope.i.img}" alt="Teacher's Photo">

        <p><strong>Name:</strong>${requestScope.i.name} </p>
        <p><strong>ID:</strong> ${requestScope.code}</p>
        <p><strong>Date of Birth:</strong> ${requestScope.i.dob}</p>
        <p><strong>Phone:</strong> ${requestScope.i.phone}</p>
        <p><strong>Gender:</strong> ${requestScope.i.gender?"Famle":"Male"}</p>
        <p><strong>Email:</strong> <a href="">${requestScope.i.email}</a></p>

    </div>
    </body>
</html>
