<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel='stylesheet' type='text/css' href="<c:url value='/app.css' />" />
<h1>Incremental Game Framework</h1>
<ul>
    <li><a href="<c:url value='/admin' />">Game Information</a></li>
    <li ><a href="<c:url value='/admin/generators' />">Generators</a></li>
    <li ><a href="<c:url value='/admin/events' />">Events</a></li>
</ul>
<div class='container'>
    <div class='left'>
        <form method='POST'>
            <label for='name'>Event name</label><br>
            <input name='eventName' id='name' value="<c:if test='${index != -1}'>${events.get(index).name}</c:if>" type='text' /><br>
            <label for='description'>Event Description</label><br>
            <textarea name='eventDescription' type='text'><c:if test="${index != -1}">${events.get(index).description}</c:if></textarea><br>
            <label for='triggerAt'>Trigger At</label><br>
            <input name='eventTriggerAt' id='triggerAt' value='<c:if test="${index != -1}">${events.get(index).triggerAt}</c:if>' type='text' /><br><button>Add | Edit</button></form>
    </div>
    <table>
        <tr>
            <td>Name</td>
            <td>Description</td>
            <td>Trigger At</td>
            <td>Action</td>
        </tr>
        <c:forEach items="${events}" var="event">
            <tr>
                <td>
                    <div class="name">${event.name}</div>
                </td>
                <td>
                    <div class="description">${event.description}</div>
                </td>
                <td>${event.triggerAt}</td>
                <td><a href='events?id=${event.id}'>edit</a> | <a href='events?deleteId=${event.id}'>delete</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <div class='name'></div>
            </td>
            <td>
                <div class='description'></div>
            </td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>
                <div class='name'></div>
            </td>
            <td>
                <div class='description'></div>
            </td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>
                <div class='name'></div>
            </td>
            <td>
                <div class='description'></div>
            </td>
            <td></td>
            <td></td>
        </tr>
    </table>
</div>