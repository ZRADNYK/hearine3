<#import "../common.ftl" as c>
<#include "../security.ftl">
<@c.page>
    <h3>${userChannel.username}</h3>
    <#if !isCurrentUser>
        <#if !isSubscriber>
            <a class="btn btn-info" href="/user/subscribe/${userChannel.id}">Subscribe</a>
        </#if>
        <#if isSubscriber>
            <a class="btn btn-info" href="/user/unsubscribe/${userChannel.id}">Unsubscribe</a>
        </#if>
    </#if>
    <#if isCurrentUser>
        <a class="btn btn-info" href="/user/${userChannel.id}/settings">Settings</a>
    </#if>
    <div class="container my-3">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscriptions</div>
                        <h3 class="card-text">
                            <a href="/user/subscriptions/${userChannel.id}/list">${subscriptionsCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscribers</div>
                        <h3 class="card-text">
                            <a href="/user/subscribers/${userChannel.id}/list">${subscribersCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#if user??>
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

    </#if>

</@c.page>