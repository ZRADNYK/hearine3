<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/song" class="form-inline">
                <input type="text" name="songFilter" class="form-control" value="${songFilter!}"
                       placeholder="Search by name">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <#include "parts/song/songEdit.ftl" />

    <#include "parts/song/songList.ftl" />

</@c.page>
