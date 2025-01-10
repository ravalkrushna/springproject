<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
    <h2>Sign Up</h2>
    <form action="/saveuser" method="post">
        <label>First Name:</label>
        <input type="text" name="firstName" required>
        <br>
        <label>Email:</label>
        <input type="email" name="email" required>
        <br>
        <label>Password:</label>
        <input type="password" name="password" required>
        <br>
        <button type="submit">Sign Up</button>
    </form>
    <p th:if="${error}" th:text="${error}"></p>
</body>
</html>
