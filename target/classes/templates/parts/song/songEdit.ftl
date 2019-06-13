<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Song editor
</a>
<div class="collapse <#if song??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if song??>${song.publicName}</#if>" name="name"
                       placeholder="Введите название песни"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if song??>${song.releaseDate}</#if>" name="releaseDate" placeholder="Дата релиза">
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
                <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if song??>${song.length}</#if>" name="length" placeholder="Длительность">
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
      <!--      <input type="hidden" name="_csrf" value="csrf.token}"/> -->
            <input type="hidden" name="id" value="<#if song??>${song.id}</#if>"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save song</button>
            </div>
        </form>
    </div>
</div>

