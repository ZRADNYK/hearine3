<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Album editor
</a>
<div class="collapse <#if album??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if album??>${album.name}</#if>" name="name" placeholder="Введите name"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if (album[bands][name])??>${album[bands][name]}</#if>" name="bands" placeholder="Исполнители">
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if (album[tags][name])??>${album[tags][name]}</#if>" name="tags" placeholder="Тэги">
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if (album[genres][name])??>${album[genres][name]}</#if>" name="genres" placeholder="Жанры">
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
      <!--      <input type="hidden" name="_csrf" value="_csrf.token}"/> -->
            <input type="hidden" name="id" value="<#if (album[id])??>${album[id]}</#if>"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save album</button>
            </div>
        </form>
    </div>
</div>

