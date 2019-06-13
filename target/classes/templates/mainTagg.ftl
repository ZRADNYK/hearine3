<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/tag" class="form-inline">
                <input type="text" name="tagFilter" class="form-control" value="${tagFilter!}"
                       placeholder="Search by name">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <#include "parts/tag/tagEdit.ftl" />

    <#include "parts/tag/tagList.ftl" />


</@c.page>
