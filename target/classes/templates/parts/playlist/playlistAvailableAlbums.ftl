<#include "../security.ftl">
<#import "../common.ftl" as c>

<@c.page>
    <form action="/playlist/${playlist.id}/availableAlbums" METHOD="post">
        <div class="card-columns">
            <#list albums as album>
                <div class="card my-3">
                    <div class="m-2">
                        <#if album.avatar??>
                            <img src=${album.avatar} class="card-img-top">
                        </#if>
                        <i>Name: ${album.name} <input type="checkbox" name="${album.name}">
                        <label for="add">Add</label></i><br/>
                        <i>Genres: <#list album.genres as genre>${genre.name}<#sep>, </#list><br/>
                            <i>Artists: <#list album.bands as band>${band.name}<#sep>, </i></#list><br/>
                            <i>Tags: <#list album.tags as tag>${tag.name}<#sep>, </i></#list><br/>
                            <input type="hidden" value="${album.id}" name="albumId">
                    </div>
                </div>
            <#else>
                No albums
            </#list>
        </div>
   <!--     <input type="hidden" name="_csrf" value="_csrf.token}"/> -->
        <button type="submit" class="btn btn-primary ml-2">Add to album</button>
    </form>
</@c.page>
