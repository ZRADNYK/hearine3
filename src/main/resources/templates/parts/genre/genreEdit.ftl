<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Genre editor
</a>
<div class="collapse <#if genre??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if genre??>${genre.name}</#if>" name="name"
                       placeholder="Введите название жанра"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            </div>
     <!--       <input type="hidden" name="_csrf" value="$csrf.token}"/> -->
            <input type="hidden" name="id" value="<#if genre??>${genre.id}</#if>"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save genre</button>
            </div>
        </form>
    </div>


