<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/playlist" class="form-inline">
                <input type="text" name="playlistFilter" class="form-control" value="${playlistFilter?ifExists}"
                       placeholder="Search by name">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <#include "parts/playlist/playlistEdit.ftl" />

    <#include "parts/playlist/playlistList.ftl" />


</@c.page>
