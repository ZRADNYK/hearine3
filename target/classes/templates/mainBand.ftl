<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/band" class="form-inline">
                <input type="text" name="artistFilter" class="form-control" value="${filter!}" placeholder="Search by name">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <#include "parts/band/bandEdit.ftl" />

    <#include "parts/band/bandList.ftl" />

</@c.page>
