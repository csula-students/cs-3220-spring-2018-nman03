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
      <form method='POST'><label for='name'>Generator name:</label><br>
         <input name='generatorName' id='name' value="<c:if test='${index != -1}'>${generators.get(index).name}</c:if>" type='text' /><br>
         <label for='rate'>Generator Rate</label><br>
         <input name='generatorRate' id='rate' value="<c:if test='${index != -1}'>${generators.get(index).rate}</c:if>" type='text' /><br>
         <label for='cost'>Unlock At</label><br>
         <input name='generatorCost' id='cost' value="<c:if test='${index != -1}'>${generators.get(index).baseCost}</c:if>" type='text' /><br>
         <label for='unlockAt'>Unlock At</label><br>
         <input name='generatorUnlockAt' id='unlockAt' value="<c:if test='${index != -1}'>${generators.get(index).unlockAt}</c:if>" type='text' /><br>
         <label for='description'>Generator Description</label><br>
         <textarea name='generatorDescription' type='text'><c:if test='${index != -1}'>${generators.get(index).description}</c:if></textarea>
         <br><button>Add | Edit</button></form>
   </div>
   <table>
      <tr>
         <td>Name</td>
         <td>Rate</td>
         <td>Cost</td>
         <td>Unlock At</td>
         <td>Action</td>
      </tr>
      <c:forEach items="${generators}" var="generator">
            <tr>
                <td>
                    <div class="name">${generator.name}</div>
                </td>
                <td>
                    <div class="name">${generator.rate}</div>
                </td>
                <td>
                    <div class="name">${generator.baseCost}</div>
                </td>
                <td>
                    <div class="name">${generator.unlockAt}</div>
                </td>
                <td><a href='generators?id=${generator.id}'>edit</a> | <a href='generators?deleteId=${generator.id}'>delete</a></td>
            </tr>
        </c:forEach>
      <tr>
         <td>
            <div class='name'></div>
         </td>
         <td>
            <div class='rate'></div>
         </td>
         <td>
            <div class='cost'></div>
         </td>
         <td>
            <div class='unlockAt'></div>
         </td>
         <td></td>
      </tr>
      <tr>
         <td>
            <div class='name'></div>
         </td>
         <td>
            <div class='rate'></div>
         </td>
         <td>
            <div class='cost'></div>
         </td>
         <td>
            <div class='unlockAt'></div>
         </td>
         <td></td>
      </tr>
      <tr>
         <td>
            <div class='name'></div>
         </td>
         <td>
            <div class='rate'></div>
         </td>
         <td>
            <div class='cost'></div>
         </td>
         <td>
            <div class='unlockAt'></div>
         </td>
         <td></td>
      </tr>
   </table>
</div>