<#include "../security.ftl">

<div class="card-columns">
    <#list tags as tag>
        <div class="card my-3">
            <div class="m-2">
                <i>Id: ${tag.id}</i><br/>
                <i>Genre: ${tag.name}</i><br/>
            </div>
        </div>
    <#else>
        No tags
    </#list>
</div>
