<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Hearine</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="/user/userlist">User list</a>
            </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/playlist">Playlist</a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/song">Songs</a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/song/add">Upload Songs</a>
                </li>
            </#if>
            <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="/tag">Tags</a>
            </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/genre">Genres</a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/album">Albums</a>
                </li>
            </#if>
            <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="/user/${user.id}/profile">Profile</a>
            </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/band">Bands</a>
                </li>
            </#if>
        </ul>
        <div class="navbar-text mr-3"><#if user??>${name}<#else>Please, login</#if></div>
        <@l.logout />
    </div>
</nav>
