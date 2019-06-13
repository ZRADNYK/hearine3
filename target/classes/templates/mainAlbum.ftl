<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/album" class="form-inline">
                <input type="text" name="albumFilter" class="form-control" value="${albumFilter!}"
                       placeholder="Search by name">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <#include "parts/album/albumEdit.ftl" />

    <#include "parts/album/albumList.ftl" />


</@c.page>
