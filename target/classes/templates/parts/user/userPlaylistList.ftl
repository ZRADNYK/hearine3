<#include "../security.ftl">

<div class="card-columns">
    <#list playlists as playlist>
        <div class="card my-3">
            <#if playlist.avatar??>
                <img src=${playlist.avatar} class="card-img-top">
            </#if>
            <div class="m-2">
                <i>Name: ${playlist.name}</i><br/>
                <span>Desc: ${playlist.dsc}</span><br/>
            </div>
            <div class="card-footer text-muted">
                <a href="/playlist/${playlist.id}/songList">Song list</a></br>
                <i>Author: <a href="/user/${playlist.author.id}/profile">${playlist.author.username}</a></i></br>
                <#if playlist.author.username == user.username>
                    <a href="/playlist/${playlist.id}/availableSongs">Add songs</a>
                    <a href="/playlist/${playlist.id}/availableAlbums">Add albums</a>
                </#if>
            </div>
        </div>
    <#else>
        <#if isCurrentUser??>
            You haven't created any playlist
        </#if>
         <#if !isCurrentUser??>
        This user didn't share any playlist yet
        </#if>
    </#list>
</div>
