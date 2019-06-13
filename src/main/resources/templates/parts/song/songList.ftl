<#include "../security.ftl">

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list songs as song>
        <tr>
         <td><audio controls>
                <source src= ${song.songPath} type="audio/ogg; codecs=vorbis">
                Тег audio не поддерживается вашим браузером.
            </audio>
            ${song.publicName}</td>
            <td></td>
        </tr>
    </#list>
    </tbody>
</table>

