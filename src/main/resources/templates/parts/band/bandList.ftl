<#include "../security.ftl">

<div class="card-columns">
    <#list bands as band>
        <div class="card my-3">
            <#if band.avatar??>
                <img src="/img/${band.avatar}" class="card-img-top">
            </#if>
            <div class="m-2">
                <i>Id: ${band.id}</i><br/>
                <i>Name: ${band.name}</i><br/>
            </div>
            <div class="card-footer text-muted">
                <a href="/band/${band.id}/albumList">Album list</a>
            </div>
        </div>
    <#else>
        No bands
    </#list>
</div>
