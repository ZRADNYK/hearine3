<#include "../security.ftl">

<div class="card-columns">
    <#list albums as album>
        <div class="card my-3">
            <div class="m-2">
                <#if album.avatar??>
                    <img src= "${album.avatar}" class="card-img-top">
                </#if>
                <i>Name: ${album.name}</i><br/>
                <i>Genres: <#list album.genres as genre>${genre.name}<#sep>, </#list><br/>
                <i>Artists: <#list album.bands as band>${band.name}<#sep>, </i></#list><br/>
                <i>Tags: <#list album.tags as tag>${tag.name}<#sep>, </i></#list><br/>
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
