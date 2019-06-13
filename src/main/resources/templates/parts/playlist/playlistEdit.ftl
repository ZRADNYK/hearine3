<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Playlist editor
</a>
<div class="collapse <#if playlist??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if playlist??>${playlist.name}</#if>" name="name"
                       placeholder="Введите название плейлиста"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if playlist??>${playlist.lstType}</#if>" name="lstType" placeholder="Тип плейлиста">
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if playlist??>${playlist.dsc}</#if>" name="dsc" placeholder="Описание">
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="lstAccess" id="public" value="public" checked>
                <label class="form-check-label" for="exampleRadios1">
                    Public playlist
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="lstAccess" id="private" value="private">
                <label class="form-check-label" for="exampleRadios2">
                    Private playlist
                </label>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
      <!--      <input type="hidden" name="_csrf" value="$csrf.token}"/> -->
            <input type="hidden" name="id" value="<#if playlist??>${playlist.id}</#if>"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save playlist</button>
            </div>
        </form>
    </div>
</div>

