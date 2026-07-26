<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Marks Entry</title>
</head>
<body>
<h2>Enter Marks</h2>
<form action="${pageContext.request.contextPath}/student/calc" method="post">
    Mark 1: <input type="number" name="mark1" required /><br/><br/>
    Mark 2: <input type="number" name="mark2" required /><br/><br/>
    Mark 3: <input type="number" name="mark3" required /><br/><br/>
    Mark 4: <input type="number" name="mark4" required /><br/><br/>
    Mark 5: <input type="number" name="mark5" required /><br/><br/>
    <input type="submit" value="Calculate Result" />
</form>
</body>
</html>