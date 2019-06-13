<#include "../security.ftl">
<#import "../common.ftl" as c>

<@c.page>
    <form action="/playlist/${playlist.id}/availableSongs" METHOD="post">
        <p>${playlist.name}</p>
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
  <!--      <input type="hidden" name="_csrf" value="_csrf.token}"/> -->
        <button type="submit" class="btn btn-primary ml-2">Add to playlist</button>
    </form>
</@c.page>
