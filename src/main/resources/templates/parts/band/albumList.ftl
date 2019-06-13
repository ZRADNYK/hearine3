<#include "../security.ftl">

<#import "../common.ftl" as c>
<@c.page>
<div class="card-columns">
    <#list albums as album>
        <div class="card my-3">
                <#if album.avatar??>
                <img class="card-img-top" src=${album.avatar} />    
                </#if>
            <div class="m-2">
                <i>Name: ${album.name}</i><br/>
                <i>Genres: <#list album.genres as genre>${genre.name}<#sep>, </#list><br/>
                    <i>Artists: <#list album.bands as bnd>${bnd.name}</i><#sep>, </#list><br/>
                    <i>Tags: <#list album.tags as tag>${tag.name}</i><#sep>, </#list><br/>
         </div>
            <div class="card-footer text-muted">
                <a href="/album/${album.id}/songList">Song list</a>
                <a href="/album/${album.id}/availableSongs">Add songs</a>
            </div>
        </div>
    <#else>
        No albums
    </#list>
</div>
</@c.page>