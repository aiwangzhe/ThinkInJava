package com.wangzhe.hadoop;

import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;
import org.apache.hadoop.hdfs.protocol.ClientProtocol;
import org.apache.hadoop.hdfs.protocol.HdfsConstants;
import org.apache.hadoop.hdfs.protocol.proto.*;
import org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolPB;
import org.apache.hadoop.ipc.ProtocolInfo;
import org.apache.hadoop.security.proto.SecurityProtos;

@ProtocolInfo(protocolName = HdfsConstants.CLIENT_NAMENODE_PROTOCOL_NAME,
        protocolVersion = 1)
public class MyNameNodePB implements ClientNamenodeProtocolPB {

    @Override
    public ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto getBlockLocations(RpcController controller, ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto getServerDefaults(RpcController controller, ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.CreateResponseProto create(RpcController controller, ClientNamenodeProtocolProtos.CreateRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.AppendResponseProto append(RpcController controller, ClientNamenodeProtocolProtos.AppendRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.SetReplicationResponseProto setReplication(RpcController controller, ClientNamenodeProtocolProtos.SetReplicationRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto setStoragePolicy(RpcController controller, ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.UnsetStoragePolicyResponseProto unsetStoragePolicy(RpcController controller, ClientNamenodeProtocolProtos.UnsetStoragePolicyRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto getStoragePolicy(RpcController controller, ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto getStoragePolicies(RpcController controller, ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.SetPermissionResponseProto setPermission(RpcController controller, ClientNamenodeProtocolProtos.SetPermissionRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.SetOwnerResponseProto setOwner(RpcController controller, ClientNamenodeProtocolProtos.SetOwnerRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.AbandonBlockResponseProto abandonBlock(RpcController controller, ClientNamenodeProtocolProtos.AbandonBlockRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.AddBlockResponseProto addBlock(RpcController controller, ClientNamenodeProtocolProtos.AddBlockRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto getAdditionalDatanode(RpcController controller, ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.CompleteResponseProto complete(RpcController controller, ClientNamenodeProtocolProtos.CompleteRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto reportBadBlocks(RpcController controller, ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.ConcatResponseProto concat(RpcController controller, ClientNamenodeProtocolProtos.ConcatRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.TruncateResponseProto truncate(RpcController controller, ClientNamenodeProtocolProtos.TruncateRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.RenameResponseProto rename(RpcController controller, ClientNamenodeProtocolProtos.RenameRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.Rename2ResponseProto rename2(RpcController controller, ClientNamenodeProtocolProtos.Rename2RequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.DeleteResponseProto delete(RpcController controller, ClientNamenodeProtocolProtos.DeleteRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.MkdirsResponseProto mkdirs(RpcController controller, ClientNamenodeProtocolProtos.MkdirsRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetListingResponseProto getListing(RpcController controller, ClientNamenodeProtocolProtos.GetListingRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.RenewLeaseResponseProto renewLease(RpcController controller, ClientNamenodeProtocolProtos.RenewLeaseRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.RecoverLeaseResponseProto recoverLease(RpcController controller, ClientNamenodeProtocolProtos.RecoverLeaseRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetFsStatsResponseProto getFsStats(RpcController controller, ClientNamenodeProtocolProtos.GetFsStatusRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetFsReplicatedBlockStatsResponseProto getFsReplicatedBlockStats(RpcController controller, ClientNamenodeProtocolProtos.GetFsReplicatedBlockStatsRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetFsECBlockGroupStatsResponseProto getFsECBlockGroupStats(RpcController controller, ClientNamenodeProtocolProtos.GetFsECBlockGroupStatsRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto getDatanodeReport(RpcController controller, ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto getDatanodeStorageReport(RpcController controller, ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto getPreferredBlockSize(RpcController controller, ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.SetSafeModeResponseProto setSafeMode(RpcController controller, ClientNamenodeProtocolProtos.SetSafeModeRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.SaveNamespaceResponseProto saveNamespace(RpcController controller, ClientNamenodeProtocolProtos.SaveNamespaceRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.RollEditsResponseProto rollEdits(RpcController controller, ClientNamenodeProtocolProtos.RollEditsRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.RestoreFailedStorageResponseProto restoreFailedStorage(RpcController controller, ClientNamenodeProtocolProtos.RestoreFailedStorageRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.RefreshNodesResponseProto refreshNodes(RpcController controller, ClientNamenodeProtocolProtos.RefreshNodesRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.FinalizeUpgradeResponseProto finalizeUpgrade(RpcController controller, ClientNamenodeProtocolProtos.FinalizeUpgradeRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.UpgradeStatusResponseProto upgradeStatus(RpcController controller, ClientNamenodeProtocolProtos.UpgradeStatusRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.RollingUpgradeResponseProto rollingUpgrade(RpcController controller, ClientNamenodeProtocolProtos.RollingUpgradeRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto listCorruptFileBlocks(RpcController controller, ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.MetaSaveResponseProto metaSave(RpcController controller, ClientNamenodeProtocolProtos.MetaSaveRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetFileInfoResponseProto getFileInfo(RpcController controller,
                ClientNamenodeProtocolProtos.GetFileInfoRequestProto request) throws ServiceException {
        System.out.println("I was invoked!");
//        ClientNamenodeProtocolProtos.GetFileInfoResponseProto.newBuilder()
//                .setFs()
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetLocatedFileInfoResponseProto getLocatedFileInfo(RpcController controller, ClientNamenodeProtocolProtos.GetLocatedFileInfoRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto addCacheDirective(RpcController controller, ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto modifyCacheDirective(RpcController controller, ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto removeCacheDirective(RpcController controller, ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto listCacheDirectives(RpcController controller, ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.AddCachePoolResponseProto addCachePool(RpcController controller, ClientNamenodeProtocolProtos.AddCachePoolRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto modifyCachePool(RpcController controller, ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto removeCachePool(RpcController controller, ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.ListCachePoolsResponseProto listCachePools(RpcController controller, ClientNamenodeProtocolProtos.ListCachePoolsRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto getFileLinkInfo(RpcController controller, ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetContentSummaryResponseProto getContentSummary(RpcController controller, ClientNamenodeProtocolProtos.GetContentSummaryRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.SetQuotaResponseProto setQuota(RpcController controller, ClientNamenodeProtocolProtos.SetQuotaRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.FsyncResponseProto fsync(RpcController controller, ClientNamenodeProtocolProtos.FsyncRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.SetTimesResponseProto setTimes(RpcController controller, ClientNamenodeProtocolProtos.SetTimesRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.CreateSymlinkResponseProto createSymlink(RpcController controller, ClientNamenodeProtocolProtos.CreateSymlinkRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetLinkTargetResponseProto getLinkTarget(RpcController controller, ClientNamenodeProtocolProtos.GetLinkTargetRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto updateBlockForPipeline(RpcController controller, ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.UpdatePipelineResponseProto updatePipeline(RpcController controller, ClientNamenodeProtocolProtos.UpdatePipelineRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public SecurityProtos.GetDelegationTokenResponseProto getDelegationToken(RpcController controller, SecurityProtos.GetDelegationTokenRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public SecurityProtos.RenewDelegationTokenResponseProto renewDelegationToken(RpcController controller, SecurityProtos.RenewDelegationTokenRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public SecurityProtos.CancelDelegationTokenResponseProto cancelDelegationToken(RpcController controller, SecurityProtos.CancelDelegationTokenRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto setBalancerBandwidth(RpcController controller, ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto getDataEncryptionKey(RpcController controller, ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.CreateSnapshotResponseProto createSnapshot(RpcController controller, ClientNamenodeProtocolProtos.CreateSnapshotRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.RenameSnapshotResponseProto renameSnapshot(RpcController controller, ClientNamenodeProtocolProtos.RenameSnapshotRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.AllowSnapshotResponseProto allowSnapshot(RpcController controller, ClientNamenodeProtocolProtos.AllowSnapshotRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.DisallowSnapshotResponseProto disallowSnapshot(RpcController controller, ClientNamenodeProtocolProtos.DisallowSnapshotRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetSnapshottableDirListingResponseProto getSnapshottableDirListing(RpcController controller, ClientNamenodeProtocolProtos.GetSnapshottableDirListingRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.DeleteSnapshotResponseProto deleteSnapshot(RpcController controller, ClientNamenodeProtocolProtos.DeleteSnapshotRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetSnapshotDiffReportResponseProto getSnapshotDiffReport(RpcController controller, ClientNamenodeProtocolProtos.GetSnapshotDiffReportRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetSnapshotDiffReportListingResponseProto getSnapshotDiffReportListing(RpcController controller, ClientNamenodeProtocolProtos.GetSnapshotDiffReportListingRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.IsFileClosedResponseProto isFileClosed(RpcController controller, ClientNamenodeProtocolProtos.IsFileClosedRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public AclProtos.ModifyAclEntriesResponseProto modifyAclEntries(RpcController controller, AclProtos.ModifyAclEntriesRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public AclProtos.RemoveAclEntriesResponseProto removeAclEntries(RpcController controller, AclProtos.RemoveAclEntriesRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public AclProtos.RemoveDefaultAclResponseProto removeDefaultAcl(RpcController controller, AclProtos.RemoveDefaultAclRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public AclProtos.RemoveAclResponseProto removeAcl(RpcController controller, AclProtos.RemoveAclRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public AclProtos.SetAclResponseProto setAcl(RpcController controller, AclProtos.SetAclRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public AclProtos.GetAclStatusResponseProto getAclStatus(RpcController controller, AclProtos.GetAclStatusRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public XAttrProtos.SetXAttrResponseProto setXAttr(RpcController controller, XAttrProtos.SetXAttrRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public XAttrProtos.GetXAttrsResponseProto getXAttrs(RpcController controller, XAttrProtos.GetXAttrsRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public XAttrProtos.ListXAttrsResponseProto listXAttrs(RpcController controller, XAttrProtos.ListXAttrsRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public XAttrProtos.RemoveXAttrResponseProto removeXAttr(RpcController controller, XAttrProtos.RemoveXAttrRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.CheckAccessResponseProto checkAccess(RpcController controller, ClientNamenodeProtocolProtos.CheckAccessRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public EncryptionZonesProtos.CreateEncryptionZoneResponseProto createEncryptionZone(RpcController controller, EncryptionZonesProtos.CreateEncryptionZoneRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public EncryptionZonesProtos.ListEncryptionZonesResponseProto listEncryptionZones(RpcController controller, EncryptionZonesProtos.ListEncryptionZonesRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public EncryptionZonesProtos.ReencryptEncryptionZoneResponseProto reencryptEncryptionZone(RpcController controller, EncryptionZonesProtos.ReencryptEncryptionZoneRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public EncryptionZonesProtos.ListReencryptionStatusResponseProto listReencryptionStatus(RpcController controller, EncryptionZonesProtos.ListReencryptionStatusRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public EncryptionZonesProtos.GetEZForPathResponseProto getEZForPath(RpcController controller, EncryptionZonesProtos.GetEZForPathRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ErasureCodingProtos.SetErasureCodingPolicyResponseProto setErasureCodingPolicy(RpcController controller, ErasureCodingProtos.SetErasureCodingPolicyRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ErasureCodingProtos.UnsetErasureCodingPolicyResponseProto unsetErasureCodingPolicy(RpcController controller, ErasureCodingProtos.UnsetErasureCodingPolicyRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetCurrentEditLogTxidResponseProto getCurrentEditLogTxid(RpcController controller, ClientNamenodeProtocolProtos.GetCurrentEditLogTxidRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetEditsFromTxidResponseProto getEditsFromTxid(RpcController controller, ClientNamenodeProtocolProtos.GetEditsFromTxidRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ErasureCodingProtos.GetErasureCodingPoliciesResponseProto getErasureCodingPolicies(RpcController controller, ErasureCodingProtos.GetErasureCodingPoliciesRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ErasureCodingProtos.AddErasureCodingPoliciesResponseProto addErasureCodingPolicies(RpcController controller, ErasureCodingProtos.AddErasureCodingPoliciesRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ErasureCodingProtos.RemoveErasureCodingPolicyResponseProto removeErasureCodingPolicy(RpcController controller, ErasureCodingProtos.RemoveErasureCodingPolicyRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ErasureCodingProtos.EnableErasureCodingPolicyResponseProto enableErasureCodingPolicy(RpcController controller, ErasureCodingProtos.EnableErasureCodingPolicyRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ErasureCodingProtos.DisableErasureCodingPolicyResponseProto disableErasureCodingPolicy(RpcController controller, ErasureCodingProtos.DisableErasureCodingPolicyRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ErasureCodingProtos.GetErasureCodingPolicyResponseProto getErasureCodingPolicy(RpcController controller, ErasureCodingProtos.GetErasureCodingPolicyRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ErasureCodingProtos.GetErasureCodingCodecsResponseProto getErasureCodingCodecs(RpcController controller, ErasureCodingProtos.GetErasureCodingCodecsRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.GetQuotaUsageResponseProto getQuotaUsage(RpcController controller, ClientNamenodeProtocolProtos.GetQuotaUsageRequestProto request) throws ServiceException {
        return null;
    }

    @Override
    public ClientNamenodeProtocolProtos.ListOpenFilesResponseProto listOpenFiles(RpcController controller, ClientNamenodeProtocolProtos.ListOpenFilesRequestProto request) throws ServiceException {
        return null;
    }
}
