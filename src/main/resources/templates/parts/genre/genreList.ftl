<#include "../security.ftl">

<div class="card-columns">
    <#list genres as genre>
        <div class="card my-3">
            <div class="m-2">
                <i>Id: ${genre.id}</i><br/>
                <i>Genre: ${genre.name}</i><br/>
            </div>
        </div>
    <#else>
        No genres
    </#list>
</div>
