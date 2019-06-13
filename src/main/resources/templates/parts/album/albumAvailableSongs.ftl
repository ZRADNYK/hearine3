<#include "../security.ftl">
<#import "../common.ftl" as c>

<@c.page>
    <form action="/album/${album.id}/availableSongs" METHOD="post">
        <p>${album.name}</p>
        <#list songs as song>
            <table>
                <tr>
                    <td>${song.publicName}</td>
                    <td> <input type="checkbox" name="${song.privateName}"> </td>
                    <td><label for="add">Add</label></td>
                    <td></td>
                </tr>
            </table>
            <input type="hidden" value="${song.id}" name="songId">
        </#list>
     <!--   <input type="hidden" name="_csrf" value="_csrf.token}"/> -->
        <button type="submit" class="btn btn-primary ml-2">Add to album</button>
    </form>
</@c.page>
