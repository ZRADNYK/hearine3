<#import "../common.ftl" as c>

<@c.page>
    <h3>${userChannel.username}</h3>
    <div>${type}</div>
    <ul class="list-group">
        <#list users as user>
            <li class="list-group-item">
                <a href="/user/${user.id}/profile">${user.getUsername()}</a>
            </li>
        </#list>
    </ul>
</@c.page>

