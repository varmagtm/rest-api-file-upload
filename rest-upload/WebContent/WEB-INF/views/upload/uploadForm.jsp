<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Resume upload via REST API.</title>
    </head>
    <body>
        <form:form modelAttribute="userProfile" method="post" enctype="multipart/form-data" action="/rest-upload/rest/upload/cv">
            <fieldset>
                <legend>Upload Fields</legend>
 
                <p>
                    <form:label for="file" path="file">File Upload:</form:label><br/>
                    <form:input path="file" type="file"/>
                </p>
 
                <p>
                    <form:label for="firstname" path="firstname">First Name:</form:label><br/>
                    <form:input path="firstname"/>
                </p>
                
                <p>
                    <form:label for="lastname" path="lastname">Last Name:</form:label><br/>
                    <form:input path="lastname"/>
                </p>
                
                <p>
                    <form:label for="email" path="email">Email:</form:label><br/>
                    <form:input path="email"/>
                </p>
                
                <p>
                    <form:label for="jobtitle" path="jobtitle">Job Title:</form:label><br/>
                    <form:input path="jobtitle"/>
                </p>
                
                <p>
                    <form:label for="source" path="source">Source:</form:label><br/>
                    <form:input path="source"/>
                </p>
 
                <p>
                    <input type="submit" />
                </p>
 
            </fieldset>
        </form:form>
    </body>
</html>