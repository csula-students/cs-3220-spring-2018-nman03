

<link rel='stylesheet' type='text/css' href='/cs3220stu47/app.css' />
<h1>Incremental Game Framework</h1>
<ul>
    <li><a href='/cs3220stu47/admin/'>Game Information</a></li>
    <li ><a href='/cs3220stu47/admin/generators'>Generators</a></li>
    <li ><a href='/cs3220stu47/admin/events'>Events</a></li>
</ul>
<div class='container'>
    <div class='left'>
        <form method='POST'><label for='name'>Event name</label><br><input name='eventName' id='name' type='text' /><br><label for='description'>Event Description</label><br><textarea name='eventDescription' type='text'></textarea><br><label for='triggerAt'>Trigger At</label><br><input name='eventTriggerAt' id='triggerAt' type='text' /><br><button>Add | Edit</button></form>
    </div>
    <table>
        <tr>
            <td>Name</td>
            <td>Description</td>
            <td>Trigger At</td>
            <td>Action</td>
        </tr>
        <tr>
            <td>
                <div class='name'>Grandma shows up</div>
            </td>
            <td>
                <div class='description'>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</div>
            </td>
            <td>10</td>
            <td><a href='events?id=0'>edit</a> | <a href='events?deleteId=0'>delete</a></td>
        </tr>
        <tr>
            <td>
                <div class='name'>You can create a factory now!</div>
            </td>
            <td>
                <div class='description'>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</div>
            </td>
            <td>50</td>
            <td><a href='events?id=1'>edit</a> | <a href='events?deleteId=1'>delete</a></td>
        </tr>
        <tr>
            <td>
                <div class='name'>We've found cookies in deep mounain ... in the mine?</div>
            </td>
            <td>
                <div class='description'>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</div>
            </td>
            <td>200</td>
            <td><a href='events?id=2'>edit</a> | <a href='events?deleteId=2'>delete</a></td>
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