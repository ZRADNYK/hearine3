<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Tag editor
</a>
<div class="collapse <#if tag??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if tag??>${tag.name}</#if>" name="name"
                       placeholder="Введите тег"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
         <!--   <input type="hidden" name="_csrf" value="$csrf.token}"/> -->
            <input type="hidden" name="id" value="<#if tag??>${tag.id}</#if>"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save tag</button>
            </div>
        </form>
    </div>
</div>

